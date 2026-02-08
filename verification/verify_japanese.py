from playwright.sync_api import sync_playwright

def run(playwright):
    browser = playwright.chromium.launch()
    page = browser.new_page()
    try:
        page.goto("http://localhost:3000/prediction")

        # Wait for page load
        page.wait_for_selector("text=重み設定", timeout=10000)

        # Check for Japanese labels
        content = page.content()

        if "スピード" in content and "スタミナ" in content:
            print("Japanese labels found.")
        else:
            print("Japanese labels NOT found.")
            exit(1)

        page.screenshot(path="verification/prediction_japanese.png")
        print("Screenshot taken")

    except Exception as e:
        print(f"Error: {e}")
        exit(1)
    finally:
        browser.close()

with sync_playwright() as playwright:
    run(playwright)
